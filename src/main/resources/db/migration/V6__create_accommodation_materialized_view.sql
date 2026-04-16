create materialized view accommodation_preview_view as
select category, count(*) as number_of_accommodations, sum(num_rooms) as number_of_rooms, AVG(num_rooms)::float4 AS average_number_of_rooms
from accommodations
group by category;

create unique index idx_accommodation_preview_view_id
    on accommodation_preview_view(category);