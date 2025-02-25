package com.salesianos.triana.DoradoMoises_Ready2Ref.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@AllArgsConstructor
public abstract class GenericSpecificationBuilder<U> {

    private List<SearchCriteria> params;

    public Specification<U> build() {
        if (params.isEmpty()) {
            return null;
        }

        log.info("Adding first specification " + params.get(0));
        Specification<U> result = build(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            log.info("Adding new specification " + params.get(i));
            result = result.and(build(params.get(i)));
        }

        log.info("Final Specification: " + result);
        return result;
    }

    private Specification<U> build(SearchCriteria criteria) {
        return (Root<U> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            switch (criteria.operation()) {
                case ">":
                    return builder.greaterThanOrEqualTo(root.get(criteria.key()), criteria.value().toString());
                case "<":
                    return builder.lessThanOrEqualTo(root.get(criteria.key()), criteria.value().toString());
                case ":":
                    if (root.get(criteria.key()).getJavaType() == String.class) {
                        return builder.like(root.get(criteria.key()), "%" + criteria.value() + "%");
                    } else {
                        return builder.equal(root.get(criteria.key()), criteria.value());
                    }
                case "=":  // Nuevo operador de igualdad estricta
                    return builder.equal(root.get(criteria.key()), criteria.value());
                default:
                    return null;
            }
        };
    }

    public static List<SearchCriteria> parseSearchQuery(String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return params;
    }
}
