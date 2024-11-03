package pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories;

import pt.psoft.g1.psoftg1.shared.infrastructure.repositories.NameMapper;
import pt.psoft.g1.psoftg1.usermanagement.infrastructure.repositories.mysql.entities.UserEntity;
import pt.psoft.g1.psoftg1.usermanagement.model.User;

public class UserMapper {
    public static UserEntity toEntity(User user) {
//        UserEntity userEntity = new UserEntity();

//        userEntity.setId();
//        userEntity.setCreatedAt(user.getCreatedAt());
//        userEntity.setEnabled(user.isEnabled());
//        userEntity.setModifiedAt(user.getModifiedAt());
//        userEntity.setModifiedBy(user.getModifiedBy());
//        userEntity.setName(NameMapper.toEntity(user.getName()));
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        userEntity.setVersion(user.getVersion());
        UserEntity userEntity = new UserEntity( user.getUsername(), user.getPassword(), NameMapper.toEntity(user.getName()));
        user.getAuthorities().forEach(authority -> userEntity.addAuthority(authority));
        return userEntity;
    }

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setCreatedAt(userEntity.getCreatedAt());
        user.setEnabled(userEntity.isEnabled());
        user.setModifiedAt(userEntity.getModifiedAt());
        user.setModifiedBy(userEntity.getModifiedBy());
        user.setName(NameMapper.toModel(userEntity.getName()).getName());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setVersion(userEntity.getVersion());
        userEntity.getAuthorities().forEach(authority -> user.addAuthority(authority));
        return user;
    }
}
