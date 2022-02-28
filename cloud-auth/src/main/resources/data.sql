INSERT INTO cloud_member
    (username, password, name, role, status, updatedAt, createdAt)
VALUES
    ('admin',
     '$2a$10$n/aFLF3mQpdtxJ7bBqNdYu9OqW27PsS/r5CIYfS1wSmS7dQgrvT72',
     'someone',
     'ROLE_ADMIN',
     'MEMBER', now(), now());

INSERT INTO cloud_member
    (username, password, name, role, status, updatedAt, createdAt)
VALUES
    ('user',
     '$2a$10$YZQKwjC0N9mRtP1AjYx2XeXQ17v9nhTEUdEFuHu0ETxjUmbzmTzRC',
     'someone',
     'ROLE_USER',
     'MEMBER', now(), now());

INSERT INTO cloud_member.oauth_client_details
    (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
values
    ('foo',
     null,
     '$2a$10$EzcE.DDyaxNv.mDw6uJbzem39LyjLhb2cBl/p3RjhKc.4maQD3tXK',
     'read,write,profile,email',
     'authorization_code,password,client_credentials,implicit,refresh_token',
     'http://localhost:8002/callback,http://localhost:8080',
     'ROLE_USER',
     36000, 50000, null, null);