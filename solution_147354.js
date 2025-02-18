function solution(data, col, row_begin, row_end) {
    const sorted = getSort(data,col-1);
    const slice = sorted.slice(row_begin-1,row_end);
    const result = slice.map((tp,i)=>
        tp.reduce((a,cur)=> (cur%(row_begin+i)) + a ,0)
    )
    
    return result.reduce((a,cur)=> a^cur , 0);
}

function getSort(data, col){
    return data.sort((a,b)=> {
        if(a[col] === b[col]){
            return b[0]- a[0];
        }
        return a[col] - b[col];
    })
    
}
