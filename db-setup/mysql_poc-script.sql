start transaction;

-- Address
insert into address (city, country, name, state, zip_code) values ("New York", "US", "Street 9th", "NY", 12345);
set @address_id_1 = last_insert_id();

insert into address (city, country, name, state, zip_code) values ("Boston", "US", "Lewiston St", "MA", 67892);
set @address_id_2 = last_insert_id();

-- Broker Type
insert into broker_type (name) values ("Firm") ;
set @broker_type_id_1 = last_insert_id();

insert into broker_type (name) values ("Person");
set @broker_type_id_2 = last_insert_id();

-- Advisor
insert into advisor (name, status) values ("Mia", "Active");
set @adivisor_id_1 = last_insert_id();

insert into advisor (name, status) values ("John", "Active");
set @adivisor_id_2 = last_insert_id();

insert into advisor (name, status) values ("Peter", "Active");
set @adivisor_id_3 = last_insert_id();

insert into advisor (name, status) values ("Joseph", "Active");
set @adivisor_id_4 = last_insert_id();

-- Broker
insert into broker (name, description, address_id, broker_type_id) values ('Broker One', 'Description of Broker One', @address_id_1, @broker_type_id_1);
set @broker_id_1 = last_insert_id();

insert into broker (name, description, address_id, broker_type_id) values ('Broker Two', 'Description of Broker Two',  @address_id_2, @broker_type_id_2);
set @broker_id_2 = last_insert_id();

-- Address Association
update address set broker_id = @broker_id_1 WHERE id = @address_id_1;
update address set broker_id = @broker_id_2 WHERE id = @address_id_2;

-- Broker-Advisor Association
insert into broker_advisor (broker_id, advisor_id) values (@broker_id_1, @adivisor_id_1), (@broker_id_1, @adivisor_id_2);
insert into broker_advisor (broker_id, advisor_id) values (@broker_id_2, @adivisor_id_3), (@broker_id_2, @adivisor_id_4);

-- Broker-Broker Association
insert into broker_broker (broker_id, associated_broker_id) values (@broker_id_1, @broker_id_2);

commit;