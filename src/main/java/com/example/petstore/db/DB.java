package com.example.petstore.db;
import java.util.ArrayList;
import java.util.List;
import com.example.petstore.Pet;
import com.example.petstore.PetType;
import com.example.petstore.PetTypeResource;

public class DB {
    private static List<Pet> petTable=new ArrayList<Pet>();
    private static List<PetType> typeTable=new ArrayList<PetType>();

    public static List<Pet> getPetTable(){
        return petTable;
    }
    public static Pet savePet(Pet pet){
        petTable.add(pet);
        return pet;
    }
    public static Pet updatePet(Pet pet, Pet newpet){
        pet.setPetName(newpet.getPetName());
        pet.setPetAge(newpet.getPetAge());
        pet.setPetType(newpet.getPetType());
        return pet;
    }
    public static Pet deletePet(Pet pet){
        petTable.remove(pet);
        return pet;
    }


    public static List<PetType> getTypeTable(){
        return typeTable;
    }
    public static PetType savePetType(PetType type){
        typeTable.add(type);
        return type;
    }
    public static PetType updateType(PetType type, PetType newType){
        type.setPetTypeId(newType.getPetTypeId());
        type.setPetTypeName(newType.getPetTypeName());
        return type;
    }
    public static PetType deleteType(PetType type){
        typeTable.remove(type);
        return type;
    }
}
