middle([], L, M):-M is L/2.
middle([H|T], L, M1):-L1 is L+1, middle(T, L1, M), M1 is M-1, check_if_middle(M, H).

check_if_middle(M, X):-middle_not_reached, M < 1,!, add_solution(X).
check_if_middle(_, _).

pre_search:-assert(solution(notfound)).
middle_not_reached:-solution(notfound).
add_solution(X):-retract(solution(notfound)), assert(solution(X)).
collect_solution(X):-retract(solution(X)).

middle(L, M):-pre_search, middle(L, 0, _), collect_solution(M).