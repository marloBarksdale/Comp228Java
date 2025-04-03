
-- Drop Sequences if they exist
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE games_seq';
EXCEPTION WHEN OTHERS THEN NULL; 
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE players_seq';
EXCEPTION WHEN OTHERS THEN NULL; 
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE player_game_seq';
EXCEPTION WHEN OTHERS THEN NULL; 
END;
/

-- Drop Tables if they exist
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PlayerGame';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Players';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Games';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- Create Sequences
CREATE SEQUENCE games_seq START WITH 1001 INCREMENT BY 1;
CREATE SEQUENCE players_seq START WITH 200 INCREMENT BY 1;
CREATE SEQUENCE player_game_seq START WITH 4001 INCREMENT BY 1;

-- Create Tables


CREATE TABLE Players (
    player_id NUMBER(4),
    first_name VARCHAR2(20) NOT NULL,
    last_name VARCHAR2(20) NOT NULL,
    address VARCHAR2(20),
    postal_code VARCHAR2(12),
    province VARCHAR2(2),
    phone_number VARCHAR2(15),
    CONSTRAINT players_player_id_pk PRIMARY KEY (player_id)
);

CREATE TABLE Games (
    game_id NUMBER(4),
    game_title VARCHAR2(20) NOT NULL,
    CONSTRAINT game_game_id_pk PRIMARY KEY (game_id)
--    CONSTRAINT game_title_unique UNIQUE( (game_title))
);

CREATE TABLE PlayerGame (
    player_game_id NUMBER(4),
    game_id NUMBER(4),
    player_id NUMBER(4),
    CONSTRAINT player_game_id_pk PRIMARY KEY (player_game_id),
    CONSTRAINT unique_player_game UNIQUE (player_id, game_id),
    CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES Games(game_id) ON DELETE CASCADE,
    CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES Players(player_id) ON DELETE CASCADE
);

-- Insert Games
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Chess');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Monopoly');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Scrabble');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Catan');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Uno');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Clue');
INSERT INTO Games (game_id, game_title) VALUES (games_seq.NEXTVAL, 'Risk');

-- Insert Players
INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Alice', 'Smith', '123 Main St', 'A1B2C3', 'ON', '123-456-7890');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Bob', 'Johnson', '456 Oak Rd', 'X1Y2Z3', 'BC', '987-654-3210');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Charlie', 'Brown', '789 Pine St', 'L5G3N8', 'QC', '514-222-3333');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Dana', 'White', '101 Maple Ave', 'T3H4K1', 'AB', '403-123-4567');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Eli', 'Turner', '202 Cedar Ln', 'N2L6R9', 'ON', '647-888-9999');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'Fiona', 'Lee', '303 Birch Rd', 'H2X3Z4', 'QC', '438-555-1212');

INSERT INTO Players (player_id, first_name, last_name, address, postal_code, province, phone_number)
VALUES (players_seq.NEXTVAL, 'George', 'Martin', '404 Elm St', 'R2J5E7', 'MB', '204-111-7777');


-- Insert PlayerGame with updated sequence IDs
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1001, 200);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1002, 201);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1003, 202);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1004, 203);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1005, 203);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1006, 204);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1007, 205);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1001, 205);
INSERT INTO PlayerGame (player_game_id, game_id, player_id) VALUES (player_game_seq.NEXTVAL, 1002, 206);

COMMIT;
