CREATE TABLE 'members_number'(
    'id' varchar(36),
    PRIMARY KEY ('id')
);
CREATE TABLE 'role' (
    'id' varchar(36),
    'name' text,
    'members_number_id' varchar(36),
    'description' text,
    PRIMARY KEY ('id'),
    FOREIGN KEY ('members_number_id') REFERENCES 'members_number'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'members'(
    'id' varchar(36),
    'family_name' text,
    'name' text,
    'adress' text,
    'members_number_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('members_number_id') REFERENCES 'members_number'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'is_part_of'(
    'id' varchar(36),
    'teams_id' varchar(36),
    'members_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('members_id') REFERENCES 'members'('id') ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY ('teams_id') REFERENCES 'teams'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'teams'(
    'id' varchar(36),
    'name' text,
    'activity' text,
    PRIMARY KEY ('id')
);
CREATE TABLE 'room'(
    'id' varchar(36),
    'size' text,
    'adress' text,
    'inside' boolean,
    'name' text,
    'description' text,
    PRIMARY KEY ('id')
);
CREATE TABLE 'room_plan'(
    'id' varchar(36),
    'teams_id' varchar(36),
    'room_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('room_id') REFERENCES 'room'('id') ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY ('teams_id') REFERENCES 'teams'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'inventory'(
    'id' varchar(36),
    'name' text,
    'stock' int,
    'room_id' varchar(36),
    'description' text,
    'date' date,
    PRIMARY KEY ('id'),
    FOREIGN KEY ('room_id') REFERENCES 'room'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'inventory_list'(
    'id' varchar(36),
    'teams_id' varchar(36),
    'inventory_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('teams_id') REFERENCES 'teams'('id') ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY ('inventory_id') REFERENCES 'inventory'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'finances'(
    'id' varchar(36),
    'name' text,
    'value' int,
    'type' text,
    'label' text,
    'source' text,
    'inventory_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('inventory_id') REFERENCES 'inventory'('id') ON DELETE RESTRICT ON UPDATE CASCADE
);
CREATE TABLE 'finance_flow'(
    'id' varchar(36),
    'finances_id' varchar(36),
    'members_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('finances_id') REFERENCES 'finances'('id') ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY ('members_id') REFERENCES 'members' ('id') ON DELETE RESTRICT ON UPDATE CASCADE
);


