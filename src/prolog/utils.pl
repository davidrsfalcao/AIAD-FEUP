distance2points(X1-Y1,X2-Y2, Distance):-
    A #= X2-X1,
    A2 #= A*A,
    B #= Y2-Y1,
    B2 #= B*B,
    C #= A2+B2,
    Distance #= C.

splitInfo([[P|_]|Oh], [P|T]):-
    splitInfo(Oh,T).

splitInfo([],[]).

nthElement(N, List, Elem):-
    nthElement(N, 0, List, Elem).

nthElement(N, N, [Elem|_], Elem).

nthElement(N, Count, [_|T], Elem):-
    Count1 is Count+1,
    nthElement(N,Count1,T,Elem).

teste(Arr):-
    length(Arr, 5),
    domain(Arr,0,9),

    sum(Arr, #=, Sum),
    labeling([maximize(Sum)], Arr).

indexes(List, Indexes):-
    length(List,N),
    N1 is N+1,
    indexes(N1,1,Indexes).

indexes(N,N,[]).

indexes(N, I, [I|T]):-
    I1 is I+1,
    indexes(N,I1,T).

indexesN(List, Indexes):-
    length(List,N),
    NN is -1-N,
    indexesN(NN,-1,Indexes).

indexesN(N,N,[]).

indexesN(N, I, [I|T]):-
    I1 is I-1,
    indexesN(N,I1,T).
