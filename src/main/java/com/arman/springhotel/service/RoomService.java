package com.arman.springhotel.service;

import com.arman.springhotel.entity.Room;
import com.arman.springhotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElse(null);
    }

    public Room createOrUpdateRoom(Room room) {
        Room newRoom = new Room();
        newRoom.setId(room.getId());
        newRoom.setCapacity(room.getCapacity());
        newRoom.setRoomClass(room.getRoomClass());
        newRoom.setAvailable(room.isAvailable());
        newRoom.setPricePerNight(room.getPricePerNight());

        return roomRepository.save(newRoom);
    }

    public void deleteRoom(int id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Room not found with id: " + id);
        }
    }
}
