package com.kodilla.library.domain.DtoX;

import com.kodilla.library.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeBookStatusDto {
    private Long bookCopyId;
    private BookStatus bookStatus;
}
