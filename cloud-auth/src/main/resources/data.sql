INSERT INTO cloud_member(loginId, loginPw, name, role, status, updatedAt, createdAt)
VALUES ('admin', '$2a$10$n/aFLF3mQpdtxJ7bBqNdYu9OqW27PsS/r5CIYfS1wSmS7dQgrvT72' ,'someone','ROLE_ADMIN','MEMBER', now(), now());

INSERT INTO cloud_member(loginId, loginPw, name, role, status, updatedAt, createdAt)
VALUES ('user', '$2a$10$YZQKwjC0N9mRtP1AjYx2XeXQ17v9nhTEUdEFuHu0ETxjUmbzmTzRC' ,'someone','ROLE_USER','MEMBER', now(), now());