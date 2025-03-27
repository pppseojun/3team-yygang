package com.beyond3.yyGang.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardLikeId is a Querydsl query type for BoardLikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardLikeId extends BeanPath<BoardLikeId> {

    private static final long serialVersionUID = 1948826124L;

    public static final QBoardLikeId boardLikeId = new QBoardLikeId("boardLikeId");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBoardLikeId(String variable) {
        super(BoardLikeId.class, forVariable(variable));
    }

    public QBoardLikeId(Path<? extends BoardLikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardLikeId(PathMetadata metadata) {
        super(BoardLikeId.class, metadata);
    }

}

