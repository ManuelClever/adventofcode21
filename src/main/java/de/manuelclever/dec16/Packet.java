package de.manuelclever.dec16;

import java.util.List;

public class Packet {
    int version;
    int type;
    long value;
    List<Packet> subPackets;

    int lengthBinary;

    public Packet(int version, int type, long value, int valueLength) {
        this.version = version;
        this.type = type;
        this.value = value;

        this.lengthBinary = 6 + valueLength;
    }

    public Packet(int version, int type, int valueLength, List<Packet> subPackets) {
        this.version = version;
        this.type = type;
        this.value = -1;
        this.subPackets = subPackets;

        this.lengthBinary = 6 + valueLength;
        for(Packet subPacket : subPackets) {
            this.lengthBinary += subPacket.lengthBinary;
        }
    }

    public boolean isFinalPacket() {
        return value != -1;
    }

    public int versionSum() {
        if(isFinalPacket()) {
            return version;
        } else {
            int versionSum = version;
            for(Packet subPacket : subPackets) {
                versionSum += subPacket.versionSum();
            }
            return versionSum;
        }
    }
}
