DROP TABLE IF EXISTS system_user CASCADE;
CREATE TABLE system_user (
    id                  SERIAL PRIMARY KEY NOT NULL,

    name                VARCHAR(255) NOT NULL,

    username            VARCHAR(255),

    email               VARCHAR(255),

    password            VARCHAR(255) NOT NULL,

    role                VARCHAR(255)
); 

-- DROP TYPE IF EXISTS circle_type CASCADE;
-- CREATE TYPE circle_type AS ENUM ('Outstation','Local');

DROP TABLE IF EXISTS trip CASCADE;
CREATE TABLE trip (
    id                  SERIAL PRIMARY KEY NOT NULL,

    -- Date of trip in epoch format
    trip_date           INTEGER NOT NULL,

    -- Id of the vehicle details row from vehicle table
    vehicle_id          INTEGER NOT NULL,

    -- It can be either Outstation or Local
    trip_circle         VARCHAR(255) NOT NULL,

    -- distance traveled by the customer
    distance            NUMERIC NOT NULL,

    -- number of hours customer had the vehicle
    number_of_hours     NUMERIC NOT NULL,

    -- this trip is coming under this slab
    slab_id             INTEGER NOT NULL,

    -- slab amount
    slab_amount         NUMERIC NOT NULL,

    -- exceeded hours after slab time range
    extra_hours         NUMERIC NULL,

    -- exceeded distance after slab range
    extra_distance      NUMERIC NULL,

    extra_amount        NUMERIC NULL,

    -- amount given by the customer to the driver in the trip
    driver_advance      NUMERIC NULL,

    -- amount given by the office to the driver while starting a trip
    office_advance      NUMERIC NULL,

    check_post_fee      NUMERIC NULL,

    parking_toll_fee    NUMERIC NULL,

    total_amount        NUMERIC NOT NULL,

    comments            text,

    -- record created time
    created_epoch       INTEGER NOT NULL,

    -- created user id
    created_user_id     INTEGER NOT NULL,

    -- updated time if any
    updated_epoch       INTEGER,

    -- updated user id
    updated_user_id     INTEGER
);

DROP TABLE IF EXISTS vehicle CASCADE;
CREATE TABLE vehicle (
    id                  SERIAL PRIMARY KEY NOT NULL,

    v_number            VARCHAR(255) NOT NULL,

    make                VARCHAR(255),

    created_epoch       INTEGER NOT NULL,

    created_user_id     INTEGER NOT NULL

); 


-- we should not allow user to delete or update this table
-- they can only add an entry to the table
-- each entry should be combined to show it to the user
-- eg: time - 4 and distance - 40 means 4/40 slab

DROP TABLE IF EXISTS trip_slab CASCADE;
CREATE TABLE trip_slab (
    id                  SERIAL PRIMARY KEY NOT NULL,

    time                NUMERIC NOT NULL,

    distance            NUMERIC NOT NULL,

    created_epoch       INTEGER NOT NULL,

    created_user_id     INTEGER NOT NULL
);



DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    id                  SERIAL PRIMARY KEY NOT NULL,

    name                VARCHAR(200)  NOT NULL,

    mobile              VARCHAR(200),

    email               VARCHAR(250),               

    address             VARCHAR(300),

    created_epoch       INTEGER NOT NULL,

    created_user_id     INTEGER NOT NULL
);


DROP TABLE IF EXISTS customer_vehicle;
CREATE TABLE customer_vehicle (
    id                  SERIAL PRIMARY KEY NOT NULL,

    customer_id         INTEGER NOT NULL,

    vehicle_number      VARCHAR(100) NOT NULL,

    vehicle_make        VARCHAR(200) NOT NULL,

    created_epoch       INTEGER NOT NULL,

    created_user_id     INTEGER NOT NULL
);


DROP TABLE IF EXISTS service_particulars;
CREATE TABLE service_particulars (
    id                  SERIAL PRIMARY KEY NOT NULL,

    service_name        VARCHAR(150) NOT NULL,

    is_multiple         BOOLEAN NOT NULL DEFAULT FALSE,

    is_free_service     BOOLEAN NOT NULL DEFAULT FALSE,  

    created_epoch       INTEGER NOT NULL,

    created_user_id     INTEGER NOT NULL
);


DROP TABLE IF EXISTS service_bill;
CREATE TABLE service_bill(
    id                     SERIAL PRIMARY KEY NOT NULL,

    vehicle_id             INTEGER NOT NULL,

    comments               VARCHAR(300),

    total_amount           NUMERIC NOT NULL,

    free_checkup_date      INTEGER NULL,                            

    free_checkup_completed INTEGER NULL DEFAULT 0,    

    created_epoch          INTEGER  NOT NULL,

    created_user_id        INTEGER  NOT NULL

);


DROP TABLE IF EXISTS service_detail;
CREATE TABLE service_detail(
    id                    SERIAL PRIMARY KEY NOT NULL,

    service_particular_id   INTEGER NOT NULL,

    service_bill_id       INTEGER NOT NULL,

    quantity              INTEGER NULL,

    amount                NUMERIC NOT NULL

);


DROP TABLE IF EXISTS sms_template;
CREATE TABLE sms_template (
    id                    SERIAL PRIMARY KEY NOT NULL,

    -- template text
    template              TEXT NOT NULL,

    created_user_id       INTEGER NOT NULL,

    created_epoch         INTEGER NOT NULL

);

DROP TABLE IF EXISTS template_parameter;
CREATE TABLE template_parameter (
    id                    SERIAL PRIMARY KEY NOT NULL,

    -- Parameter name eg: CustomerName, VehicleNumber
    name                  VARCHAR(255) NOT NULL,

    -- Value reference for this parameter eg: customer.name for CustomerName and
    -- customer_vehicle.vehicle_number for VehicleNumber
    value_ref             VARCHAR(255) NOT NULL,

    created_user_id       INTEGER NOT NULL,

    created_epoch         INTEGER NOT NULL

);

DROP TABLE IF EXISTS sms_queue;
CREATE TABLE sms_queue (
    id                    SERIAL PRIMARY KEY NOT NULL,

    -- Id of service_bill table against which this message is created
    service_bill_id       INTEGER NOT NULL,

    mobile_number         VARCHAR(255) NOT NULL,

    -- Actual SMS text with parameters replaced with actual values
    message               TEXT NOT NULL,

    -- SMS sent status
    sent                  BOOLEAN NOT NULL DEFAULT FALSE,

    created_user_id       INTEGER NOT NULL,

    created_epoch         INTEGER NOT NULL,

    updated_user_id       INTEGER NOT NULL,

    updated_epoch         INTEGER NULL,

    -- SMS sent status updated epoch
    sent_epoch            INTEGER NULL

);


INSERT INTO service_particulars (service_name, is_multiple, is_free_service, created_epoch, created_user_id) 
        VALUES    
        ('Wheel Alignment', false, true, 1439314988, 1),  
        ('Rear Wheel Alignment', false, true, 1439314988, 1), 
        ('Wheel Balance', true, false, 1439314988, 1),  
        ('Weights (in grams)', true, false, 1439314988, 1), 
        ('Automatic Tyres Change', true, false, 1439314988, 1), 
        ('Rim Bend Removing', true, false, 1439314988, 1), 
        ('Rim Tubeless Neck', true, false, 1439314988, 1), 
        ('Tyre Puncher', true, false, 1439314988, 1),
        ('Tyre Rotation', true, false, 1439314988, 1);

INSERT INTO template_parameter (name, value_ref, created_epoch, created_user_id)
        VALUES
        ('CustomerName','Customer.name', 1439314988, 1),
        ('VehicleNumber','CustomerVehicle.vehicle_number', 1439314988, 1),
        ('VehicleMake','CustomerVehicle.vehicle_make', 1439314988, 1),
        ('DueDate','ServiceBill.free_checkup_date', 1439314988, 1);

INSERT INTO sms_template (template, created_user_id, created_epoch)
        VALUES
        ('[CustomerName] Your Vehicle number [VehicleNumber] [VehicleMake] is due for free checkup. ABHI WHEELS (wheel Alignment & Emission test center For Petrol &Diesel - Due date [DueDate]', 1439314988, 1);
