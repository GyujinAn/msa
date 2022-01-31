create table if not exists ECP_ADMIN.MEMBER_HTTP_MESSAGES
(
    HTTP_MESSAGE_ID bigint auto_increment
        primary key,
    CREATED_AT      datetime     null,
    DIVISION        varchar(255) null,
    HOST            varchar(255) null,
    METHOD          varchar(255) null,
    PATH            varchar(255) null,
    PERFORMANCE     bigint       null,
    REMOTE_IP_ADDR  varchar(255) null,
    STATUS          varchar(255) null,
    USER_ID         varchar(255) null
);