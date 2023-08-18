package com.example.apptimeline.service;

import com.example.apptimeline.dto.Group;
import com.example.apptimeline.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GroupService {
    private List<Group> groups = new ArrayList<>();

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Group getGroupById(Long groupId) {
        return groups.stream()
                .filter(group -> group.getId().equals(groupId))
                .findFirst()
                .orElse(null);
    }

    public List<Group> getGroupsByUser(User user) {
        log.info("User name {}",user.getUsername());
        List<Group> groups1 = new ArrayList<>();
        for(Group group : groups){
            log.info("Group {}",group.getName());
            for (User user1 : group.getMembers()){
                if (user1.getId() == user.getId()){
                    groups1.add(group);
                }
            }
        }
        return groups1;
    }
}
