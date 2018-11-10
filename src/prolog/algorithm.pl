:-use_module(library(lists)).
:-use_module(library(clpfd)).

% index(i,x,y)
:-dynamic index/3.

:-dynamic postMan/2.

% Infos [[x-y,time-state], [x1-y1,time1-state1]]

sortOrders(Infos, Capacity, X-Y, PostOffice, Orders):-
    retractall(index(_,_,_)),
    retractall(postMan(_,_,_)),
    indexes(Infos, Index),
    indexesN(Infos, IndexNeg),
    append(Index,IndexNeg, Indexes),
    createIndexes(Infos, PostOffice, PostOffice, Indexes),
    asserta(postMan(X,Y)),
    length(Infos, Max),
    Min is 0-Max,
    length(Indexes,Size),
    length(Orders, Max),

    domain(Orders,0,Max),
    all_distinct(Orders),

    calculateDistance(Orders, Distances),
    sum(Distances, #=, Distance),

    % refersToIndex(Orders, Index, IndexNeg),
    % verifyCapacity(Orders, Capacity),
    %
    % write(Distance),

    labeling([minimize(Distance)], Orders),!,
    %labeling([minimize(Distance)], Distances),
    write(Distance).

%sortOrders([[1-1,44-44],[2-2,33-33],[3-3,99-99]],2,10-0,0-0,Orders).
%sortOrders([[1-1,44-44],[2-2,33-33],[3-3,99-99],[100-1,4-4]],2,10-0,0-0,Orders).
%sortOrders([[1-0,A],[2-0,B],[3-0,C]],4,0-0,4-0,Orders).
%sortOrders([[1-0,A],[2-0,B],[3-0,C]],4,4-0,4-0,Orders).



verifyCapacity(Orders, Cap):-
    verifyCapacity(Orders, 0, Cap).

verifyCapacity([Oh|Ot], Count, Cap):-
    Oh #> 0,
    Count1 is Count+1,
    Count1 =< Cap,
    verifyCapacity(Ot,Count1,Cap).

verifyCapacity([Oh|Ot], _, Cap):-
    Oh #< 0,
    verifyCapacity(Ot,0,Cap).

verifyCapacity([],_,_).

calculateDistance(Orders, Distances):-
    postMan(X,Y),
    calculateDistance(Orders, X-Y, Distances).

calculateDistance([], _, []).

calculateDistance([Oh|Ot], Last, [Dis|T]):-
    index(Oh,X,Y),
    distance2points(X-Y,Last,Dis),
    calculateDistance(Ot, X-Y, T).

createIndexes(Infos, _PostOffice, _PostOffice ,[I|T]):-
    I > 0,
    nth1(I,Infos,Info),
    nth1(1,Info,X-Y),
    asserta(index(I,X,Y)),
    createIndexes(Infos,_PostOffice, _PostOffice,T).

createIndexes(Infos, X-Y, _PostOffice,[I|T]):-
    I < 0,
    asserta(index(I,X,Y)),
    createIndexes(Infos,_PostOffice, _PostOffice,T).

createIndexes(_, _, _,[]).

refersToIndex([Oh|Ot]):-
    index(Oh,_,_),
    refersToIndex(Ot).

refersToIndex([]).
