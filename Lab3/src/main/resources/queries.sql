create table public.location
(
    id          BIGSERIAL PRIMARY KEY,
    name        varchar(255),
    address     varchar(255),
    capacity    varchar(255),
    description varchar(255)
);

create table public.event
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255),
    description     VARCHAR(255),
    popularityScore FLOAT8,
    locationId      INT8,
    CONSTRAINT fk_location FOREIGN KEY (locationId) REFERENCES public.location (id)
);

create table public.event_booking
(
    id              BIGSERIAL PRIMARY KEY,
    eventName       varchar(255),
    attendeeName    varchar(255),
    attendeeAddress varchar(255),
    numberOfTickets int8
);

insert into location (name, address, capacity, description)
values ('Skyline Plaza', '123 City Center, Chicago, IL', '3500',
        'A modern rooftop venue with panoramic views of the city skyline.'),
       ('Lakeside Gardens', '789 Shoreline Dr, Orlando, FL', '1200',
        'A lush, scenic venue by the lake, perfect for intimate gatherings.'),
       ('Historic Theater District', '101 Broadway St, Nashville, TN', '2500',
        'A grand theater in the heart of the historic district, offering classic charm.'),
       ('Forest Haven Lodge', '202 Woodland Ln, Portland, OR', '800',
        'A cozy lodge tucked in a serene forest setting, ideal for rustic events.'),
       ('Sunset Point Amphitheater', '678 Cliffside Ave, Malibu, CA', '5000',
        'An open-air amphitheater with breathtaking sunset views over the ocean.');

insert into event (name, description, popularityScore, locationId)
values ('Tame Impala Concert', 'Psychedelic Night in San Francisco', 9.2, 1),
       ('The National Concert', 'Intimate Evening in Brooklyn', 9.6, 2),
       ('Florence + The Machine Concert', 'Magical Performance in London', 8.9, 3),
       ('Arcade Fire Concert', 'Indie Rock Extravaganza in Montreal', 9.0, 4),
       ('Bon Iver Concert', 'Acoustic Session in Minneapolis', 8.3, 5),
       ('Vampire Weekend Concert', 'Summer Festival in Chicago', 8.7, 1),
       ('LCD Soundsystem Concert', 'Electronic Night in Detroit', 8.5, 2),
       ('Sigur Ros Concert', 'Atmospheric Sounds in Reykjavik', 9.3, 3),
       ('The Cure Concert', 'Gothic Rock Night in Amsterdam', 8.4, 4),
       ('Nine Inch Nails Concert', 'Industrial Hits Live', 9.1, 5);

select * from event;
select * from location;
select * from event_booking;



