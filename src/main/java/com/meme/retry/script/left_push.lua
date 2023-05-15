local ids_key = KEYS[1];
local queue_key = KEYS[2];
local detail_key_prefix = KEYS[3];
local current_time = tonumber(ARGV[1]);

local ids = redis.call("ZRANGE", ids_key, 0, current_time);

return ids;
