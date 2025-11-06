package adawardrobe.service;

import adawardrobe.model.Color;
import adawardrobe.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }

    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    public Optional<Color> getColorById(String id) {
        return colorRepository.findById(Long.parseLong(id));
    }

    public Optional<Color> updateColor(String id, Color colorDetails) {
        Optional<Color> color = colorRepository.findById(Long.parseLong(id));

        if (color.isPresent()) {
            Color existingColor = color.get();
            existingColor.setColor(colorDetails.getColor());

            Color updatedColor = colorRepository.save(existingColor);
            return Optional.of(updatedColor);
        }

        return Optional.empty();
    }

    public boolean deleteColor(String id) {
        Optional<Color> color = colorRepository.findById(Long.parseLong(id));

        if (color.isPresent()) {
            colorRepository.delete(color.get());
            return true;
        }
        return false;
    }
}
