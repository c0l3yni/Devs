const getUrlParams = (query) =>{
    const urlParams = new URLSearchParams(window.location.search);
    const result = urlParams.get(query);
    if(result) return result;
}
export default getUrlParams