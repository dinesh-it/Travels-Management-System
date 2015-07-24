DROP TABLE IF EXISTS system_user CASCADE;
CREATE TABLE system_user (
	id SERIAL PRIMARY KEY NOT NULL,

	name VARCHAR(255) NOT NULL,

	username VARCHAR(255),

	email VARCHAR(255),

	password VARCHAR(255) NOT NULL,

	role VARCHAR(255)	
); 

DROP TYPE IF EXISTS circle CASCADE;
CREATE TYPE circle AS ENUM ('Outstation','Local');
DROP TABLE IF EXISTS trip CASCADE;
CREATE TABLE trip (
	id SERIAL PRIMARY KEY NOT NULL,

	-- Date of trip in epoch format
	trip_date INTEGER NOT NULL,

	-- Id of the vehicle details row from vehicle table
	vehicle_id INTEGER NOT NULL,

	-- It can be either Outstation or Local
	trip_circle circle NOT NULL,

	-- distance traveled by the customer
	distance NUMERIC NOT NULL,

	-- number of hours customer had the vehicle
	number_of_hours NUMERIC NOT NULL,

	-- this trip is coming under this slab
	slab_id INTEGER NOT NULL,

    -- slab amount
    slab_amount NUMERIC NOT NULL,

    -- exceeded hours after slab time range
	extra_hours NUMERIC NULL,

    -- exceeded distance after slab range
	extra_distance NUMERIC NULL,

	extra_amount NUMERIC NOT NULL,

	total_amount NUMERIC NOT NULL,

	-- amount given by the customer to the driver in the trip
	driver_advance NUMERIC NULL,

	-- amount given by the office to the driver while starting a trip
	office_advance NUMERIC NULL,

	check_post_fee NUMERIC NULL,

	parking_tool_fee NUMERIC NULL,

	comments text,

	-- record created time
	created_epoch INTEGER NOT NULL,

	-- created user id
	created_user_id INTEGER NOT NULL,

	-- updated time if any
	updated_epoch INTEGER,

	-- updated user id
	updated_user_id INTEGER
);

DROP TABLE IF EXISTS vehicle CASCADE;
CREATE TABLE vehicle (
	id SERIAL PRIMARY KEY NOT NULL,

	v_number VARCHAR(255) NOT NULL,

	make VARCHAR(255),

	created_epoch INTEGER NOT NULL,

    created_user_id INTEGER NOT NULL

); 


-- we should not allow user to delete or update this table
-- they can only add an entry to the table
-- each entry should be combined to show it to the user
-- eg: time - 4 and distance - 40 means 4/40 slab
DROP TABLE IF EXISTS trip_slab CASCADE;
CREATE TABLE trip_slab (
	id SERIAL PRIMARY KEY NOT NULL,

	time NUMERIC NOT NULL,

	distance NUMERIC NOT NULL,

	created_epoch INTEGER NOT NULL,

	created_user_id INTEGER NOT NULL
);



-- TODO References need to be add
