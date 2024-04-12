interface Sort {
    empty: boolean;
    unsorted: boolean;
    sorted: boolean;
}

interface Pageable {
    offset: number;
    sort: Sort;
    paged: boolean;
    unpaged: boolean;
    pageSize: number;
    pageNumber: number;
}

interface Page<T> {
    totalElements: number;
    totalPages: number;
    size: number;
    content: T[];
    number: number;
    sort: Sort;
    pageable: Pageable;
    first: boolean;
    last: boolean;
    numberOfElements: number;
    empty: boolean;
}

export default Page;