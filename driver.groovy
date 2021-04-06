metadata {
    definition (
        author: "Bran Malin",
        description: "Test receipt of LAN messages",
        name: "LANTest",
        namespace: "BranMalin_LANTest"
    )
    {        
        capability "Sensor";

        attribute "LastMessageText", "string";
        attribute "LastMessageHex", "string";
        attribute "LastMessageParsed", "string";
        attribute "LastMessageBody", "string";
    }

    preferences {
        input name: "deviceIpAddress", type: "text", title: "Device IP Address", description: "IP Address to monitor for data", required: true
    }
}

private String ipStringAsHex(String humanReadableIp)
{
    String hexifiedIp = "";
    humanReadableIp.split("\\.").each({hexifiedIp += String.format('%02x', it.toInteger())});
    return hexifiedIp.toUpperCase();
}
void updated()
{
    //Notify Hubitat of our new Device Network Address.
    //The address must be a hexadecimal representation of the IP address we want to communicate with.
    String newDni = ipStringAsHex(deviceIpAddress);
    device.setDeviceNetworkId(newDni);
}

void parse(String message)
{
    sendEvent(name: "LastMessageText", value: message);
    String hexifiedMessage = "";
    message.toCharArray().each({hexifiedMessage += String.format("%02x ", (Integer) it )});
    sendEvent(name: "LastMessageHex", value: hexifiedMessage);
    sendEvent(name: "LastMessageParsed", value: parseLanMessage(message));
    sendEvent(name: "LastMessageBody", value: parseLanMessage(message).body);
}
