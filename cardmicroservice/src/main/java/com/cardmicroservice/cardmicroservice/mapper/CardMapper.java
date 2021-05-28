package com.cardmicroservice.cardmicroservice.mapper;

import com.asi.lib.dto.CardDTO;
import com.cardmicroservice.cardmicroservice.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface CardMapper {

    CardDTO toDTO(Card source);

    Card toBo(CardDTO cardDTO);
}
