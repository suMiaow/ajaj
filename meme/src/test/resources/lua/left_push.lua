local ids_key = KEYS[1];
local queue_key = KEYS[2];
local current_time = tonumber(ARGV[1]);

local ids = redis.call("ZRANGEBYSCORE", ids_key, 0, current_time);

for i, v in ipairs(ids) do
    redis.call("LPUSH", queue_key, v);
    redis.call("ZREM", ids_key, v);
end
