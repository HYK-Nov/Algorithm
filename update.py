#!/usr/bin/env python

import os
from urllib import parse

HEADER="""# 
# 백준 & 프로그래머스

제가 푼 백준과 프로그래머스 문제가 자동으로 커밋되는 곳입니다

### boj
[![Solved.ac프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj=yundori97)](https://solved.ac/yundori97)

"""

def main():
    content = ""
    content += HEADER
    
    directories = [];
    solveds = [];

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        
        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        
        if directory == '.':
            continue
            
        if directory not in directories:
            if directory in ["백준", "프로그래머스"]:
                match directory:
                    case "Bronze":
                        content += "## 🥉 {}\n".format(directory)
                    case "Silver":
                        content += "## 🥈 {}\n".format(directory)
                    case "Gold":
                        content += "## 🥇 {}\n".format(directory)
                    case _:
                        content += "## 🙇‍♀️ {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 링크 |\n"
                content += "| ----- | ----- |\n"
            directories.append(directory)

        for file in files:
            if category not in solveds:
                content += "|{}|[링크]({})|\n".format(category, parse.quote(os.path.join(root, file)))
                solveds.append(category)
                print("category : " + category)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
