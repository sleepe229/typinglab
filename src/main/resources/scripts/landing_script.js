function scrolltocontainer(containerselect, instance = 0){
    const elements = document.querySelectorAll(containerselect);
    if (elements.length > instance){
        elements[instance].scrollIntoView({behavior: 'smooth'});
    }
}
const link1 = document.getElementById("link1");
const link2 = document.getElementById("link2");
const link3 = document.getElementById("link3");

link1.addEventListener('click', () =>{
    scrolltocontainer('.block_1');
});

link2.addEventListener('click', () =>{
    scrolltocontainer('.block_2');
});

link3.addEventListener('click', () =>{
    scrolltocontainer('.block_3');
});

// let myurl = 'https://www.youtube.com'
// document.getElementById("btnsi" ).onclick = function (){
//     window.location.replace(myurl)
// }

function redirectgs(){
    window.location.href="sign_up_page.html"
}

const buttongs = document.getElementById("btngs");

buttongs.addEventListener('click', () =>{
    redirectgs()
})

