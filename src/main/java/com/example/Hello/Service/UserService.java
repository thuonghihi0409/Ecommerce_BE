package com.example.Hello.Service;

import com.example.Hello.Repository.Conversation_Repository;
import com.example.Hello.Repository.Message_Repository;
import com.example.Hello.Repository.RentalProperty_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.UserCreationRequest;
import com.example.Hello.dto.request.UserUpdataRequest;
import com.example.Hello.entity.Conversation;
import com.example.Hello.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private RentalProperty_Repository rentalPropertyRepository;
    @Autowired
    private Conversation_Repository conversationRepository;
    @Autowired
    private Message_Repository messageRepository;
    public User CreateUser (UserCreationRequest request){
        User user = new User();
        if(userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("User existed");
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setSdt(request.getSdt());
        user.setGmail(request.getGmail());
        user.setVaitro(request.getVaitro());
        user.setNgaytao(request.getNgaytao());
        user.setAvturl(request.getAvturl());
        return userRepository.save(user);
    }
    public List<User> getUsers (){
        return userRepository.findAll();
    }
    public User getUser (String id){
       return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }
    public User getUserByUsername (String username){
        return userRepository.findByUsername(username);
    }

    public User updateUser (String userId, UserUpdataRequest request){
        User user= getUser(userId);
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setSdt(request.getSdt());
        user.setGmail(request.getGmail());
        user.setVaitro(request.getVaitro());
        user.setNgaytao(request.getNgaytao());
        return userRepository.save(user);
    }
    @Transactional
    public void delateUser (String userId){
        for (Conversation conversation : conversationRepository.findByUser1_IdOrUser2_Id(userId,userId) ){
            messageRepository.deleteAllByConversation_ConversationId(conversation.getConversationId());
        }
        conversationRepository.deleteAllByUser1_IdOrUser2_Id(userId, userId);
        rentalPropertyRepository.deleteByLandlord_Id(userId);
        userRepository.deleteById(userId);
    }
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Sai username hoặc password");
        }

        return user;  // Đăng nhập thành công, trả về đối tượng user
    }
}
