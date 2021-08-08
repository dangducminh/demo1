package com.example.demo.Service;

import com.example.demo.DTO.ConvertBetweenUserAndUserDTO;
import com.example.demo.DTO.FormLogin;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.QueryByField;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.JWT.JwtCreateToken;
import com.example.demo.JWT.JwtRequestFilter;
import com.example.demo.Repository.CustomizeRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Request.RequestCreateUser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtCreateToken jwtCreateToken;

    @Autowired
    CustomizeRepository customizeRepository;


    @Override
    public UserDTO getInfo() {
        String jwtToken = JwtRequestFilter.requestPublic.getHeader("Authorization").replace("Bearer ","");
//        System.out.println(jwtToken);
//        String[] split_string = jwtToken.split("\\.");
//        String base64EncodedHeader = split_string[0];
//        String base64EncodedBody = split_string[1];
//        String base64EncodedSignature = split_string[2];
//        Base64 base64Url = new Base64(true);
//        String header = new String(base64Url.decode(base64EncodedHeader));
//        System.out.println("JWT Header : " + header);
//        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
//        String body = new String(base64Url.decode(base64EncodedBody));
//        System.out.println(body);
        String email = Jwts.parser().setSigningKey("dangducminh").parseClaimsJws(jwtToken).getBody().getSubject();
        User user = userRepository.getByEmail(email);
        UserDTO userDTO = ConvertBetweenUserAndUserDTO.ConvertUserToUserDTO(user);
        return userDTO;
    }

    @Override
    public UserDTO getInfoId(Integer id) {
        User user = userRepository.getById(id);
        UserDTO userDTO = ConvertBetweenUserAndUserDTO.ConvertUserToUserDTO(user);
        return userDTO;
    }

    @Override
    public String createUser( RequestCreateUser rcu) {
        User user = new User();
        user.setEmail(rcu.getEmail());
        user.setName(rcu.getName());
        user.setPhone(rcu.getPhone());
        user.setAddress(rcu.getAddress());
        user.setPassword(rcu.getPassword());
        user.setIsActive(true);
        user.setMathScore(rcu.getMathScore());
        user.setLiteratureScore(rcu.getLiteratureScore());
        user.setEnglishScore(rcu.getEnglishScore());
        user.setAverageScore((rcu.getMathScore()+ rcu.getLiteratureScore()+rcu.getEnglishScore())/3);
        user.setRole(roleRepository.getById(rcu.getRole()));
        System.out.println(user);
        if (userRepository.save(user)!=null){
            return "Account successfully created";
        }
        return "account creation failed";
    }

    @Override
    public String updateUser(Integer id,RequestCreateUser rcu) {
       User user = userRepository.getById(id);
       if (user==null){
           return "account not found";
       }
       if (rcu.getEmail()!=null){
           user.setEmail(rcu.getEmail());
       }
       if (rcu.getName()!=null) {
        user.setName(rcu.getName());
       }
       if (rcu.getPhone()!=null){
           user.setPhone(rcu.getPhone());
       }
       if (rcu.getAddress()!=null) {
           user.setAddress(rcu.getAddress());
       }
       if (rcu.getPassword()!=null){
           user.setPassword(rcu.getPassword());
       }
       if (rcu.getMathScore()!=0) {
           user.setMathScore(rcu.getMathScore());
       }
       if (rcu.getLiteratureScore()!=0) {
           user.setLiteratureScore(rcu.getLiteratureScore());
       }
       if (rcu.getEnglishScore()!=0) {
           user.setEnglishScore(rcu.getEnglishScore());
       }
       user.setAverageScore((user.getEnglishScore()+user.getMathScore()+user.getLiteratureScore())/3);
       if (rcu.getRole() != null) {
           user.setRole(roleRepository.getById(rcu.getRole()));
       }
       System.out.println(user);
       if (userRepository.save(user)!=null){
           return "Account successfully updated";
       }
       return "account creation failed";
    }

    @Override
    public String deleteUser(Integer id) {
        User user = userRepository.getById(id);
        if (user==null){
            return "account not found";
        }else{
            userRepository.deleteById(id);
            return "delete success";
        }
    }

    @Override
    public List<UserDTO> getUserByRole(String roleName) {
        Role role = roleRepository.getById(roleName);
        List<User> listUser = userRepository.findAllByRole(role);
        List<UserDTO> listDTO = null;
        listUser.forEach((element)->{
            listDTO.add(ConvertBetweenUserAndUserDTO.ConvertUserToUserDTO(element));
        });
        return listDTO;
    }

    @Override
    public String login(FormLogin formLogin) {
        User user = userRepository.findAllByEmailAndPassword(formLogin.getEmail(),formLogin.getPassword()).get(0);
        if (user!=null){
            String token = jwtCreateToken.generateToken(user);
            return token;
        }else{
            throw new UsernameNotFoundException("User not found with email: " + formLogin.getEmail());
        }
    }

    @Override
    public List<UserDTO> getUserByField(RequestCreateUser rcu) {


        QueryByField queryByField = new QueryByField();

        queryByField.setEmail(rcu.getEmail());
        queryByField.setName(rcu.getName());
        queryByField.setPhone(rcu.getPhone());
        queryByField.setAddress(rcu.getPhone());
        if (rcu.getRole()!=null){
            queryByField.setRole(roleRepository.getOne(rcu.getRole()));
        }
        return customizeRepository.findAllByConditions(queryByField);
    }
}
