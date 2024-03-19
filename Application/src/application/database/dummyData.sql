INSERT INTO "role" ("id", "name", "description")
VALUES ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'Administrator', 'Lese-/Schreibrechte auf alle Bereiche'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'Abteilungsleiter', 'Administrator ohne Schreibrecht auf Rollenbereich');

INSERT INTO "role_permission" ("role_id", "permission")
VALUES ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'READ_MEMBERS'),
       ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'WRITE_MEMBERS'),
       ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'READ_ROLES'),
       ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'WRITE_ROLES'),
       ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'READ_TEAMS'),
       ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'WRITE_TEAMS'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'READ_MEMBERS'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'WRITE_MEMBERS'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'READ_ROLES'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'READ_TEAMS'),
       ('8d769540-0b4c-4743-881a-0d0f9653e28f', 'WRITE_TEAMS');

INSERT INTO "team" ("id", "name", "activity")
VALUES ('0e8144d1-10e8-4a33-9a7a-030144d3ca30', 'Dortmund', 'Fußball'),
       ('18e1d1c7-fa3b-43c2-9e65-0a77aa710592', 'Segelfreunde', 'Segeln'),
       ('6aa19a28-3de8-48ac-b06b-2733cdefd9a5', 'Grün Grün Bälle', 'Golf');

INSERT INTO "member" ("id", "first_name", "last_name", "address")
VALUES ('7b53b480-02ef-4893-a6ac-7211d7c622d1', 'Heinz', 'Günter', 'Essen'),
       ('45197916-9a46-426e-bbd1-7a1d5381aaa2', 'Max', 'Mustermann', 'Bottrop'),
       ('661655c2-78b6-4366-aa72-64e15365216a', 'Hans', 'Peters', 'Hamburg'),
       ('fde9af8f-936a-496f-8b0e-2155279627c3', 'Elke', 'Jansen', 'Kiel'),
       ('818ee7d6-279e-4c02-8ba4-601282b0b8fc', 'Hendrik', 'Bach', 'München');

INSERT INTO "member_role" ("member_id", "role_id")
VALUES ('7b53b480-02ef-4893-a6ac-7211d7c622d1', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994'),
       ('45197916-9a46-426e-bbd1-7a1d5381aaa2', '8d769540-0b4c-4743-881a-0d0f9653e28f'),
       ('661655c2-78b6-4366-aa72-64e15365216a', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994'),
       ('fde9af8f-936a-496f-8b0e-2155279627c3', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994'),
       ('818ee7d6-279e-4c02-8ba4-601282b0b8fc', '8d769540-0b4c-4743-881a-0d0f9653e28f');

INSERT INTO "member_team" ("member_id", "team_id")
VALUES ('7b53b480-02ef-4893-a6ac-7211d7c622d1', '0e8144d1-10e8-4a33-9a7a-030144d3ca30'),
       ('45197916-9a46-426e-bbd1-7a1d5381aaa2', '0e8144d1-10e8-4a33-9a7a-030144d3ca30'),
       ('661655c2-78b6-4366-aa72-64e15365216a', '18e1d1c7-fa3b-43c2-9e65-0a77aa710592'),
       ('fde9af8f-936a-496f-8b0e-2155279627c3', '18e1d1c7-fa3b-43c2-9e65-0a77aa710592'),
       ('fde9af8f-936a-496f-8b0e-2155279627c3', '6aa19a28-3de8-48ac-b06b-2733cdefd9a5');
