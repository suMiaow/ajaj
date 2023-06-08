local queue_key = KEYS[1];
local detail_key_prefix = KEYS[2];

local id = redis.call("RPOP", queue_key);

local detail;

if (id) then
    local detail_key = detail_key_prefix .. id;
    detail = redis.call("GET", detail_key);
    redis.call("DEL", detail_key);
end

return detail;