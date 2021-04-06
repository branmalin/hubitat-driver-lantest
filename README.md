# hubitat-driver-lantest
Tests and diagnostic aids for developing LAN drivers for the Hubitat home automation hub.

Right now, this is just a driver that receives HTTP messages on the Hubitat service port (39501) and parses them various ways, showing the parsed values in its attributes and events. It includes no error checking at this time.

To use:
1. Install the driver code.
2. Create a virtual device of type LANTest.
3. Set the IP address property of the device to the IP address you plan on connecting to the Hubitat *from*.
4. Send HTTP messages to the Hubitat on port 39501, from the device whose IP you specified.
5. Review the device events to see various parsings of the message received.

It seems as if the Hubitat's handling strips out HTTP headers for some reason, at least past a certain point. I've found that POST requests transmit a Body to the Hubitat that is preserved in the message passed to the driver parse() method.

The body is retrieved as shown in this driver: by using the Hubitat parseLanMessage method and retrieving the "body" element of the resulting map or dictionary or whatever it is (I'm still new to Groovy so I'm not 100% sure).