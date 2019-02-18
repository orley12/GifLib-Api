package com.teamtreehouse.gif;

import com.teamtreehouse.gif.Gif;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GifRepository extends PagingAndSortingRepository<Gif, Long> {
}
