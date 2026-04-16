create or replace view accommodation_view as
select a.id, a.name, a.category, a.num_rooms, h.name as host_name, h.surname as host_surname, c.name as country_name
from accommodations a join hosts h on a.host_id = h.id
                      join countries c on h.country_id = c.id