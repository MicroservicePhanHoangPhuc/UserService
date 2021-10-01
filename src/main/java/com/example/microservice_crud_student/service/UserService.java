package com.example.microservice_crud_student.service;

import com.example.microservice_crud_student.VO.Department;
import com.example.microservice_crud_student.VO.ResponseTemplateVO;
import com.example.microservice_crud_student.entity.User;
import com.example.microservice_crud_student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).get();
        vo.setUser(user);
        Department department =
                restTemplate.getForObject("http://localhost:9001/department/"
                                + user.getDepartmentId(),
                        Department.class);

        vo.setDepartment(department);

        return vo;
    }
}
