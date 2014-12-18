unpack_coords([],_,[]).
unpack_coords([Column|Columns], Row, [[Row,Column]|NextCoords]):-
	NextRow is Row + 1, 
	unpack_coords(Columns, NextRow, NextCoords).

pick_two(L, A, B):-append(_,[A|T],L), append(_,[B|_], T).

potential_conflicts(Board, PotentialConflicts):-
	unpack_coords(Board, 1, UnpackedBoard), 
	findall([Q1,Q2], pick_two(UnpackedBoard, Q1, Q2), PotentialConflicts).

conflict([RowQ1, ColumnQ1], [RowQ2, ColumnQ2]):-abs(RowQ1-RowQ2) =:= abs(ColumnQ1-ColumnQ2).

zen_queens([]).
zen_queens([[Q1,Q2]|PotentialConflicts]):-
	\+conflict(Q1, Q2),
	zen_queens(PotentialConflicts).

find_zen_queens(Board, ZenBoard):-
	permutation(Board, ZenBoard), 
	potential_conflicts(ZenBoard, PotentialConflicts), 
	zen_queens(PotentialConflicts).

all_zen_queens(All):-findall(ZenBoard, find_zen_queens([0,1,2,3,4,5,6,7], ZenBoard), All).

