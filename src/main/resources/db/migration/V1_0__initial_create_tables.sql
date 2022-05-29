DROP TABLE IF EXISTS tgbot_users.teams;
CREATE TABLE IF NOT EXISTS tgbot_users.teams (
    id				bigserial NOT NULL PRIMARY KEY,
    team_name 		varchar(100) NOT NULL UNIQUE,
    color 			varchar(100) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS tgbot_users.users;
CREATE TABLE IF NOT EXISTS tgbot_users.users (
    chat_id			bigint NOT NULL PRIMARY KEY,
    nickname 		varchar(100) UNIQUE,
    first_name 		varchar(100) NOT NULL,
    last_name		varchar(100),
    team			bigint,
    "role" 			varchar(100),
    FOREIGN KEY (team) REFERENCES tgbot_users.teams(id)
);