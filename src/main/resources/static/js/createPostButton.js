const handleSubmit = async (event) => {
    event.preventDefault(); 
    
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    let today = new Date();
    let day = String(today.getDate()).padStart(2, '0');
    let month = String(today.getMonth() + 1).padStart(2, '0');
    let year = today.getFullYear();

    today = day + '/' + month + '/' + year;
    console.log(today)
    fetch(`/api/posts`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({title,content,today})
        }
    ).then(response =>{
        console.log(response);
        return response.json();
    }).then(data => {
        console.log(data);
        const getPostList = document.getElementById('post-lists');
        const link = document.createElement('a');
        link.href = `/post/${data.id}`;
        link.textContent = data.title;
        link.style.display = 'block';  
        getPostList.appendChild(link);

    }).catch(error => {
        console.error('error',error);
    });

}

document.addEventListener('DOMContentLoaded', () => {
 
    const form = document.querySelector('form');
    form.addEventListener('submit',handleSubmit);

});