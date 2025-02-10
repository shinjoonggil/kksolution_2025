package com.kks.kksolution.service;

import com.kks.kksolution.dto.user.RegisterUserDto;
import com.kks.kksolution.dto.user.SignInDto;
import com.kks.kksolution.entity.User;
import com.kks.kksolution.entity.UserAccount;
import com.kks.kksolution.repository.UserAccountRepository;
import com.kks.kksolution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByAccountId(username).orElseThrow(() -> new UsernameNotFoundException("account.not_found"));
    }
    @Transactional
    public void registerUser(RegisterUserDto registerUserDto) {
        User user = userRepository.save(User.builder()
                .name(registerUserDto.getName())
                .email(registerUserDto.getEmail())
                .phone(registerUserDto.getPhone())
                .contact(registerUserDto.getContact())
                .build());
        UserAccount userAccount = userAccountRepository.save(UserAccount.builder()
                .accountId(registerUserDto.getAccountId())
                .accountPassword(passwordEncoder.encode(registerUserDto.getAccountPassword()))
                .user(user)
                .build());
    }
    public void signInProcess(SignInDto signInDto){

    }
}
