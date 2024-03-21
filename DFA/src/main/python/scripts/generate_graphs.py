import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import pypyodbc as odbc
import os
import sys

sql_statement = sys.argv[1]
graph_name = sys.argv[2]
graph_choice = int(sys.argv[3])
# need to decide how to implement dynamic sizing of the graph
# do i need to return the path for the image? no, there will be naming conventions for all of them
# 1 - scatterplot, 2 - barplot, 3 - lineplot , do this in if statements

def getDataFrame(query):
    # will leave creds in python file for now, implement security later
    username = "capstoneAdmin"
    password = "Group7@Capstone"

    server = "dfaserver.database.windows.net"
    database = "DFADatabase"
    connection_string = "Driver={ODBC Driver 18 for SQL Server};Server=tcp:" + server + ",1433;Database=" + database + ";Uid=" + username + ";Pwd=" + password + ";Encrypt=yes;TrustServerCertificate=no;Connection Timeout=30;"

    conn = odbc.connect(connection_string)

    cursor = conn.cursor()
    cursor.execute(query)

    dataset = cursor.fetchall()
    columns = [column[0] for column in cursor.description]
    dataframe = pd.DataFrame(dataset, columns=columns)

    return dataframe

def graphPathDirectory():
    path = os.path.dirname(__file__)
    lst = path.split("\x5c")
    lst.pop()
    graph_path = "\x5c".join(lst) + "\x5c" + "generated_graphs" + "\x5c"
    return graph_path


def make_bar(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    sns.set_style("dark")
    sns.barplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.title(name)
    plt.savefig(graphPathDirectory() + name + ".png", dpi = 100)


def make_scatter(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    sns.set_style("white")
    sns.scatterplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.title(name)
    plt.savefig(graphPathDirectory() + name + ".png", dpi = 100)


def make_line(df, name, height, width, x_name, y_name):
    plt.figure(figsize=(width, height))
    sns.set_style("white")
    sns.lineplot(x=x_name, y=y_name, data=df)
    plt.xlabel(x_name)
    plt.ylabel(y_name)
    plt.title(name)
    plt.savefig(graphPathDirectory() + name + ".png", dpi = 100)


if __name__ == '__main__':
    if graph_choice == 1:
        make_scatter(getDataFrame(str(sql_statement)), str(graph_name), 10, 5, 'year', 'totalprofit')

    if graph_choice == 2:
        make_bar(getDataFrame(str(sql_statement)), str(graph_name), 20, 7, 'year', 'totalprofit')

    if graph_choice == 3:
        make_line(getDataFrame(str(sql_statement)), str(graph_name), 10, 15, 'year', 'totalprofit')
