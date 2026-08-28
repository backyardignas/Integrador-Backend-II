package co.edu.cesde.ga.aplicacion.service.Impl;

import co.edu.cesde.ga.aplicacion.service.GroupsService;
import co.edu.cesde.ga.aplicacion.models.Groups;
import co.edu.cesde.ga.aplicacion.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {

    private final GroupsRepository groupsRepository;

    @Autowired
    public GroupsServiceImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    @Transactional
    public Groups create(Groups group) {
        return groupsRepository.save(group);
    }

    @Override
    @Transactional
    public boolean delete(Long groupId) {
        if (!groupsRepository.existsById(groupId)) {
            return false;
        }
        groupsRepository.deleteById(groupId);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Groups groupUpdate) {
        if (groupUpdate.getGroupId() == null || !groupsRepository.existsById(groupUpdate.getGroupId())) {
            return false;
        }
        groupsRepository.save(groupUpdate);
        return true;
    }

    @Override
    public Groups findById(Long groupId) {
        return groupsRepository.findById(groupId).orElse(null);
    }

    @Override
    public List<Groups> findAll() {
        return groupsRepository.findAll();
    }

    @Override
    public boolean existsById(Long groupId) {
        return groupsRepository.existsById(groupId);
    }
}
