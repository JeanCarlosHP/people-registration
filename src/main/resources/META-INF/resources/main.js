const $btnRegister = document.querySelector('#btn-register')
$btnRegister.addEventListener('click', registerPeople)

function registerPeople () {
    const $namePeople = document.querySelector('#name-people').value
    const $agePeople = document.querySelector('#age-people').value

    const link = `http://localhost:8080/peoples/${$namePeople}/${$agePeople}`

    fetch(link, {method: 'POST'})

    createPeopleOnTable($namePeople, $agePeople)
}

const $table = document.querySelector('#table')

function loadPeoplesRegistered() {
    fetch('http://localhost:8080/peoples')
    .then(res => res.json())
    .then(addPeopleOnTable)
}

function addPeopleOnTable(peoples) {
    for (let i in peoples) {
        const name = peoples[i].name
        const age = peoples[i].age

        createPeopleOnTable(name, age)
    }
}

function createPeopleOnTable(name, age) {
    const tr = document.createElement('tr')

    const tdName = document.createElement('td')
    tdName.innerText = name

    const tdAge = document.createElement('td')
    tdAge.innerText = age

    tr.appendChild(tdName)
    tr.appendChild(tdAge)
    $table.appendChild(tr)
}

loadPeoplesRegistered()
