package adawardrobe.service;


import adawardrobe.model.Type;
import adawardrobe.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    public Type createType(Type type) {
        return typeRepository.save(type);
    }

    public Optional<Type> getTypeById(String id) {
        return typeRepository.findById(Long.parseLong(id));
    }

    public Optional<Type> updateType(String id, Type typeDetails) {
        Optional<Type> type = typeRepository.findById(Long.parseLong(id));

        if (type.isPresent()) {
            Type existingType = type.get();
            existingType.setType(typeDetails.getType());

            Type updatedType = typeRepository.save(existingType);
            return Optional.of(updatedType);
        }
        return Optional.empty();
    }

    public boolean deleteType(String id) {
        Optional<Type> type = typeRepository.findById(Long.parseLong(id));

        if(type.isPresent()){
            typeRepository.delete(type.get());
            return true;
        }
        return false;
    }
}
