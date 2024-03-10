INSERT INTO 'role' ('id', 'name', 'description')
VALUES ('27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'admin', 'admin'),
('8d769540-0b4c-4743-881a-0d0f9653e28f', 'admin_member', 'Admin Benutzer');

INSERT INTO 'members' ('id', 'last_name', 'first_name', 'address')
VALUES ('7b53b480-02ef-4893-a6ac-7211d7c622d1', 'Günter', 'Heinz', 'Essen'),
('45197916-9a46-426e-bbd1-7a1d5381aaa2', 'Mustermann', 'Max', 'Bottrop'),
('661655c2-78b6-4366-aa72-64e15365216a', 'Peters', 'Hans', 'Hamburg'),
('fde9af8f-936a-496f-8b0e-2155279627c3', 'Jansen', 'Elke', 'Kiel'),
('818ee7d6-279e-4c02-8ba4-601282b0b8fc', 'Bach', 'Hendrik', 'München');

INSERT INTO 'teams' ('id', 'name', 'activity')
VALUES ('0e8144d1-10e8-4a33-9a7a-030144d3ca30', 'Dortmund', 'Fußball'),
('018e1d1c7-fa3b-43c2-9e65-a77aa710592', 'Segelfreunde', 'Segeln'),
('6aa19a28-3de8-48ac-b06b-2733cdefd9a5', 'Grün Grün Bälle', 'Golf');

INSERT INTO 'member_role' ('id', 'members_id', 'role_id')
VALUES ('1eb464ba-88eb-4a25-aee8-29fd0c2ef208', '7b53b480-02ef-4893-a6ac-7211d7c622d1',  '27e2fc49-79fc-4bd0-9835-efb0ea6e0994'),
('e605f368-f903-461f-a835-1d9ecb7e270c', '45197916-9a46-426e-bbd1-7a1d5381aaa2', '8d769540-0b4c-4743-881a-0d0f9653e28f'),
('b302665d-6583-4198-bbcb-22b83f744709', '661655c2-78b6-4366-aa72-64e15365216a', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994'),
('1bdbd233-3e69-45e1-89fd-27aa739a0c91', 'fde9af8f-936a-496f-8b0e-2155279627c3', '8d769540-0b4c-4743-881a-0d0f9653e28f'),
('cc01ea01-d36f-4eff-8311-6b6b7866d25a', '818ee7d6-279e-4c02-8ba4-601282b0b8fc', '8d769540-0b4c-4743-881a-0d0f9653e28f');

INSERT INTO 'members_team' ('id', 'teams_id', 'members_id')
VALUES ('f7477fff-d4d1-492f-9f12-a052a55cbba5', '0e8144d1-10e8-4a33-9a7a-030144d3ca30',  '7b53b480-02ef-4893-a6ac-7211d7c622d1'),
('23594b39-dfd1-4025-b8c6-deaadb4c3931', '0e8144d1-10e8-4a33-9a7a-030144d3ca30', '45197916-9a46-426e-bbd1-7a1d5381aaa2'),
('e8509f3b-348a-4319-92e0-0b0c2b777441', '018e1d1c7-fa3b-43c2-9e65-a77aa710592', '661655c2-78b6-4366-aa72-64e15365216a'),
('426f86d4-8029-4b2f-a7de-f9a83f2dda3c', '6aa19a28-3de8-48ac-b06b-2733cdefd9a5', 'fde9af8f-936a-496f-8b0e-2155279627c3'),
('6b481bf9-2155-4774-8bc4-25f05669b98f', '6aa19a28-3de8-48ac-b06b-2733cdefd9a5', '818ee7d6-279e-4c02-8ba4-601282b0b8fc');

INSERT INTO 'permission' ('id', 'permission')
VALUES ('d7fbd085-eb53-40c7-9fe0-f7a20ab478dd', 'WRITE_MEMBERS'),
('56364a82-8c8b-4c91-ada4-71fcd2e060f8' , 'READ_MEMBERS');

INSERT INTO 'permission_role' ('id', 'role_id', 'permission_id')
VALUES ('4785bdf8-d581-4a81-b52f-911c541b3d58', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994', 'd7fbd085-eb53-40c7-9fe0-f7a20ab478dd'),
('9abd5715-1555-4de2-92f9-58bd8fc6d70d', '27e2fc49-79fc-4bd0-9835-efb0ea6e0994', '56364a82-8c8b-4c91-ada4-71fcd2e060f8'),
('e92c7951-3ea6-40c8-b3c2-ada1f71cd75a', '8d769540-0b4c-4743-881a-0d0f9653e28f', '56364a82-8c8b-4c91-ada4-71fcd2e060f8');