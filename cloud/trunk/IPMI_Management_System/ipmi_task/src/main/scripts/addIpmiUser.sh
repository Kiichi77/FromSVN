#!/bin/sh
ipmitool user set name 5 test
ipmitool user set password 5 123456
ipmitool user priv 5 4 1
ipmitool sol payload enable 1 5
ipmitool user enable 5
