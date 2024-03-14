-- ROLE
CREATE TABLE 'role' (
    'id' varchar(36),
    'name' text,
    'description' text,
    PRIMARY KEY ('id')
);
CREATE TABLE 'role_permission' (
    'role_id' varchar(36),
    'permission' text,
    PRIMARY KEY ('role_id', 'permission'),
    FOREIGN KEY ('role_id') REFERENCES 'role'('id') ON DELETE CASCADE
);

-- TEAM
CREATE TABLE 'team' (
    'id' varchar(36),
    'name' text,
    'activity' text,
    PRIMARY KEY ('id')
);

-- MEMBER
CREATE TABLE 'member' (
    'id' varchar(36),
    'first_name' text,
    'last_name' text,
    'address' text,
    PRIMARY KEY ('id')
);
CREATE TABLE 'member_role' (
    'member_id' varchar(36),
    'role_id' varchar(36),
    PRIMARY KEY ('member_id', 'role_id'),
    FOREIGN KEY ('member_id') REFERENCES 'member'('id') ON DELETE CASCADE,
    FOREIGN KEY ('role_id') REFERENCES 'role'('id') ON DELETE RESTRICT
);
CREATE TABLE 'member_team' (
    'member_id' varchar(36),
    'team_id' varchar(36),
    PRIMARY KEY ('member_id', 'team_id'),
    FOREIGN KEY ('member_id') REFERENCES 'member'('id') ON DELETE CASCADE,
    FOREIGN KEY ('team_id') REFERENCES 'team'('id') ON DELETE RESTRICT
);

-- LOCATION
CREATE TABLE 'location' (
    'id' varchar(36),
    'name' text,
    'description' text,
    'inside' boolean,
    'size' int,
    PRIMARY KEY ('id')
);

-- INVENTORY_ITEM
CREATE TABLE 'inventory_item' (
    'id' varchar(36),
    'name' text,
    'date' date,
    'activity' text,
    'location_id' varchar(36),
    PRIMARY KEY ('id'),
    FOREIGN KEY ('location_id') REFERENCES 'location'('id') ON DELETE RESTRICT
);

-- FINANCE_ITEM
CREATE TABLE 'finance_item' (
    'id' varchar(36),
    'name' text,
    'date' date,
    'value' int,
    'source' text,
    PRIMARY KEY ('id')
);
CREATE TABLE 'finance_item_label' (
    'finance_item_id' varchar(36),
    'label' text,
    PRIMARY KEY ('finance_item_id', 'label'),
    FOREIGN KEY ('finance_item_id') REFERENCES 'finance_item'('id') ON DELETE CASCADE
);
