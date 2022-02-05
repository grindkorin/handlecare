package com.example.handlecare.service.dbServices;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.security.PasswordConfig;
import com.example.handlecare.security.email.EmailSenderImpl;
import com.example.handlecare.security.token.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
    @Autowired
    ConfirmationTokenServiceImpl tokenService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RecipientServiceImpl recipientService;
    @Autowired
    DeliverServiceImpl deliverService;
    @Autowired
    EmailSenderImpl emailSender;
    @Autowired
    PasswordConfig passwordConfig;


    public boolean resetPasswordConfirmToken(String token) {
        ConfirmationToken resetToken = tokenService.findByToken(token);
        if (resetToken != null) {
            return true;
        } else {
            throw new IllegalStateException("Токен не найден");
        }
    }

    public void resetPassword(String token, String password) {
        User user = userService.getByToken(token);
        Deliver deliver = tokenService.findByToken(token)
                .getDeliver();
        if (user != null) {
            deliver.setPassword(passwordConfig.passwordEncoder().encode(password));
            deliverService.save(deliver);
        } else if (deliver == null) {
            Recipient recipient = tokenService.findByToken(token).getRecipient();
            recipient.setPassword(passwordConfig.passwordEncoder().encode(password));
            recipientService.save(recipient);
        } else {
            throw new IllegalStateException("Токен не найден");
        }
    }

    public void forgetPassword(String email) {
        User byEmail = userService.findByEmail(email);
        ConfirmationToken token = tokenService.findByDeliver(deliverService.findByEmail(email));
        if (token == null)
            token = tokenService.findByRecipient(recipientService.findByEmail(email));
        sendResetEmail(byEmail, token);
    }

    public void sendResetEmail(User user, ConfirmationToken token) {
//        String link = "http://localhost:8080/forgot_password/confirm?token=" + token.getToken();
        String link = "http://localhost:8080/reset_password?token=" + token.getToken();
        String email = buildEmail(user.getName(), link);
        emailSender.send(user.getEmail(), "Сброс пароля", email);
    }


    public String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Сброс пароля</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Здравствуйте, " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> пожалуйста, перейдите по ссылке, для сброса пароля: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Активировать</a> </p></blockquote>\n  <p>Если вы не просили об этом, то игнорируйте сообщение</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
