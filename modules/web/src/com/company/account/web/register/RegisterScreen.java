package com.company.account.web.register;

import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.PasswordField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;

import javax.inject.Inject;
import java.util.UUID;

/**
 * "Register" screen.
 */
public class RegisterScreen extends AbstractWindow {

    /**
     * ID of the Group for self-registered users.
     */
    private static final String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";

    @Inject
    private TextField loginField;
    @Inject
    private TextField emailField;

    @Inject
    private PasswordField passwordField;

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Inject
    private PasswordEncryption passwordEncryption;

    /**
     * "Cancel" button click handler.
     */
    public void onCancelBtnClick() {
        close(CLOSE_ACTION_ID);
    }

    /**
     * "OK" button click handler.
     */
    public void onOkBtnClick() {
        // Check if a user with the same login already exists
        long existing = dataManager.getCount(LoadContext.create(User.class).setQuery(
                LoadContext.createQuery("select u from sec$User u where u.loginLowerCase = :login")
                        .setParameter("login", getLogin())));
        if (existing > 0) {
            showNotification("A user with the same login already exists", NotificationType.TRAY);
        } else {
            // Load group and role to be assigned to the new user
            Group group = dataManager.load(LoadContext.create(Group.class).setId(UUID.fromString(COMPANY_GROUP_ID)));
            LoadContext<Role> loadContext = LoadContext.create(Role.class)
                    .setQuery(LoadContext.createQuery("select r from sec$Role r where r.defaultRole = true"));
            Role role = dataManager.load(loadContext);

            if (role == null) {
                throw new IllegalStateException("Can't create user: no default security role provided");
            }

            // Create a user instance
            User user = metadata.create(User.class);
            user.setLogin(getLogin());
            user.setPassword(passwordEncryption.getPasswordHash(user.getId(), getPassword()));
            user.setGroup(group);
            user.setEmail(emailField.getValue());
            // Create a link to the role
            UserRole userRole = metadata.create(UserRole.class);
            userRole.setUser(user);
            userRole.setRole(role);
            // Save new entities
            dataManager.commit(new CommitContext(user, userRole));

            showNotification("Created user " + user.getLogin(), NotificationType.TRAY);
            close(COMMIT_ACTION_ID);
        }
    }

    /**
     * @return entered login
     */
    public String getLogin() {
        return loginField.getValue();
    }

    /**
     * @return entered password
     */
    public String getPassword() {
        return passwordField.getValue();
    }
}
