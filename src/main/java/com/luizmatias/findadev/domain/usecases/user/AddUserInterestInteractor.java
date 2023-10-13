package com.luizmatias.findadev.domain.usecases.user;

import com.luizmatias.findadev.domain.entities.Interest;
import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.InterestRepository;
import com.luizmatias.findadev.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

public class AddUserInterestInteractor {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    public AddUserInterestInteractor(UserRepository userRepository, InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    public User addUserInterest(User user, Long interestId) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Interest interest = interestRepository.getInterestById(interestId);

        ArrayList<Interest> interests = new ArrayList<>(user.getInterests());

        if (interests.stream().anyMatch(interestItem -> Objects.equals(interestItem.getId(), interestId))) {
            throw new ResourceAlreadyExistsException("user already has this interest");
        }

        interests.add(interest);
        user.setInterests(interests);

        return userRepository.updateUserProfile(user);
    }

}
