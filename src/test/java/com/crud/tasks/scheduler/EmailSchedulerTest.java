package com.crud.tasks.scheduler;

import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailScheduler emailScheduler;

    @Test
    public void sendInformationEmail() {
        /*//Given
        Mail mail = new Mail("test@test.com", "test","Test Message");//, "test2@test.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //mailMessage.setCc(mail.getToCc());
        //When
        emailScheduler.sendInformationEmail();
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
        verify(emailScheduler,times(1)).sendInformationEmail();*/
    }
}
