package com.library.mariela.authservice.authservice.services;


import com.library.mariela.authservice.authservice.dtos.LoginUserDto;
import com.library.mariela.authservice.authservice.dtos.NewUserDto;
import com.library.mariela.authservice.authservice.dtos.RequestDto;
import com.library.mariela.authservice.authservice.dtos.TokenDto;
import com.library.mariela.authservice.authservice.entities.DbSequence;
import com.library.mariela.authservice.authservice.entities.User;
import com.library.mariela.authservice.authservice.repositories.IUserRepository;
import com.library.mariela.authservice.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MongoOperations mongoOperations;
    @Autowired
    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, MongoOperations mongoOperations) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.mongoOperations = mongoOperations;
    }

    public User save(NewUserDto newUserDto){
        Optional<User> userOptional = iUserRepository.findByUserName(newUserDto.getUserName());
        if (userOptional.isPresent())
            return null;
        String password = passwordEncoder.encode(newUserDto.getPassword());
        User user = User.builder()
                .id(generateSequence())
                .userName(newUserDto.getUserName())
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .password(password)
                .role(newUserDto.getRole())
                .build();
        return iUserRepository.save(user);
    }

    public TokenDto login (LoginUserDto loginUserDto){
        Optional<User> userOptional = iUserRepository.findByUserName(loginUserDto.getUserName());
        if (userOptional.isEmpty())
            return null;
        if (passwordEncoder.matches(loginUserDto.getPassword(), userOptional.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(userOptional.get()));
        else
            return null;
    }

    public TokenDto validate(String token, RequestDto dto){
        if (!jwtProvider.validate(token, dto))
            return null;
        String userName = jwtProvider.getUserNameFromToken(token);
        if (iUserRepository.findByUserName(userName).isEmpty())
            return null;
        return new TokenDto(token);
    }

    private long generateSequence(){
        DbSequence sequence = mongoOperations.findAndModify(query(where("_id").is(User.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }
}
